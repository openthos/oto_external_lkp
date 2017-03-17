# yaml文件到job.sh文件的转换过程分析：
## 过程概述
lkp run $LKP_SRC/ebizzy-200%-5x-5s.yaml  
这个命令会调用/bin/run-local   
即等效于/bin/run-local   $LKP_SRC/ebizzy-200%-5x-5s.yaml  
然后/bin/run-local会解析$LKP_SRC/ebizzy-200%-5x-5s.yaml 文件，并利用函数job2sh生成一个job.sh脚本。  

job.sh脚本的存放路径如下（以ebizzy为例）：  
/result/ebizzy/200%-5x-5s/elwin-virtual-machine/ubuntu/defconfig/gcc-5/4.4.0-45-generic/1/job.sh  
然后执行job.sh便可以执行以上yaml文件指定的测试任务。


## /bin/run-local 文件的关键地方注释：

```
jobfile = ARGV[0]  ##jobfile=$LKP_SRC/ebizzy-200%-5x-5s.yaml

##建立一个job对象，并利用ebizzy-200%-5x-5s.yaml进行初始化
job = Job.new
	job.load(jobfile) or (
		$stderr.puts "#{jobfile} is not a valid jobfile"
		exit 1
	)


##初始化(或者覆盖）一些ebizzy-200%-5x-5s.yaml中已经有的变量
##机器名字、内核编译的配置文件、内核版本号、根文件系统类型（一般是linux发行版的名字）、cpu核心数、编译器版本
job['testbox'] = HOSTNAME
job['tbox_group'] = tbox_group(HOSTNAME)
job['kconfig'] ||= 'defconfig' 
job['commit'] ||= getcommitid  ##这里的||=表示如果这个属性的值为空，则进行初始化，如果不为空，则保留原值不覆盖
job['rootfs'] ||= `grep -m1 ^ID= /etc/os-release`.split('=')[1].chomp
job['nr_cpu'] ||= '$(nproc)'
job['compiler'] ||= File.symlink?('/usr/bin/gcc') ? File.readlink('/usr/bin/gcc') : 'gcc'	


## job文件的位置为/result/ebizzy/200%-5x-5s/elwin-virtual-machine/ubuntu/defconfig/gcc-5/4.4.0-45-generic/1/job.sh 
job_script = result_root + '/job.sh'

## 通过job.to_hash和job2sh函数来把job对象生成一个shell脚本，并保存。
File.open(job_script, mode='w', perm=0775) do |file|
	job2sh(deepcopy(job.to_hash), file)
end


system job_script, 'run_job'  ##执行job.sh里面的run_job函数：即执行benchmark和mointor进行信息收集
system LKP_SRC + '/bin/post-run'    ##benchmark执行完毕以后，会kill调monitor进程
system LKP_SRC + '/monitors/event/wakeup', 'job-finished'  
system job_script, 'extract_stats'   ##执行job.sh里面的'extract_stats'函数，对monitor的原始输出进行处理为key:vale形式，供后续生产json文件。
###json文件的生成，以及多次测试的平均值、和方差计算。
```	

## job2sh函数关键地方注释：	
```
##job2sh函数是在#{LKP_SRC}/lib/job2sh.rb文件中实现的。	
/bin/run-local通过require "#{LKP_SRC}/lib/job2sh.rb"将其包含进来

###job2sh函数注释
def job2sh(job, out_script)
	$job_params = job['params'] || {}

	$script_lines = []
	$stats_lines = []

	shell_header  ##输出   "#!/bin/sh"  即shabang到job.sh文件
  $cur_func = :run_job  #没有看懂什么意思

	##输出exprot_top_env()函数到job.sh文件
	out_line "export_top_env()"   
	out_line "{"
	create_programs_hash "{setup,monitors,tests,daemon}/**/*"
	job.delete_if { |key, val| parse_one([], key, val, :PASS_EXPORT_ENV) }
	out_line
	out_line "\t[ -n \"$LKP_SRC\" ] ||"
	out_line "\texport LKP_SRC=/lkp/${user:-lkp}/src"
	out_line "}\n\n"

	out_line "run_job()"
	out_line "{"
	out_line
	out_line "\techo $$ > $TMP/run-job.pid"
	out_line
	out_line "\t. $LKP_SRC/lib/job.sh"
	out_line "\t. $LKP_SRC/lib/env.sh"
	out_line
	out_line "\texport_top_env"
	out_line
	parse_hash [], job   ###在这里输出了需要运行哪些moinitor
	out_line "}\n\n"

	$cur_func = :extract_stats
	out_line "extract_stats()"   ###输出了测试结果抽取的函数
	out_line "{"
	create_programs_hash "stats/**/*"
	parse_hash [], job
	out_line
	out_line $stats_lines
	parse_hash [], YAML.load_file(LKP_SRC + '/etc/default_stats.yaml')
	out_line "}\n\n"

	out_line '"$@"'

	out_script.puts $script_lines
end
```
