#!/bin/sh

##此函数的作用是初始化一些环境变量
export_top_env()
{
	export testcase='ebizzy'
	export category='benchmark'
	export enqueue_time='2016-11-07 01:16:00 -0500'
	export testbox='elwin-virtual-machine'
	export tbox_group='elwin-virtual-machine'
	export kconfig='defconfig'
	export commit='4.4.0-45-generic'
	export rootfs='ubuntu'
	export nr_cpu="$(nproc)"
	export compiler='gcc-5'

	[ -n "$LKP_SRC" ] ||
	export LKP_SRC=/lkp/${user:-lkp}/src
}


#用来执行这个job.sh
run_job()
{
	echo $$ > $TMP/run-job.pid

	. $LKP_SRC/lib/job.sh
	. $LKP_SRC/lib/env.sh

	export_top_env

	default_monitors()
	{
		run_monitor $LKP_SRC/monitors/event/wait 'activate-monitor'
		run_monitor $LKP_SRC/monitors/wrapper kmsg
		run_monitor $LKP_SRC/monitors/wrapper uptime
		run_monitor $LKP_SRC/monitors/wrapper iostat
		run_monitor $LKP_SRC/monitors/wrapper vmstat
		run_monitor $LKP_SRC/monitors/wrapper numa-numastat
		run_monitor $LKP_SRC/monitors/wrapper numa-vmstat
		run_monitor $LKP_SRC/monitors/wrapper numa-meminfo
		run_monitor $LKP_SRC/monitors/wrapper proc-vmstat
		export interval=10
		run_monitor $LKP_SRC/monitors/wrapper proc-stat
		unset interval

		run_monitor $LKP_SRC/monitors/wrapper meminfo
		run_monitor $LKP_SRC/monitors/wrapper slabinfo
		run_monitor $LKP_SRC/monitors/wrapper interrupts
		run_monitor $LKP_SRC/monitors/wrapper lock_stat
		run_monitor $LKP_SRC/monitors/wrapper latency_stats
		run_monitor $LKP_SRC/monitors/wrapper softirqs
		run_monitor $LKP_SRC/monitors/wrapper bdi_dev_mapping
		run_monitor $LKP_SRC/monitors/wrapper diskstats
		run_monitor $LKP_SRC/monitors/wrapper nfsstat
		run_monitor $LKP_SRC/monitors/wrapper cpuidle
		run_monitor $LKP_SRC/monitors/wrapper cpufreq-stats
		run_monitor $LKP_SRC/monitors/wrapper turbostat
		run_monitor $LKP_SRC/monitors/wrapper pmeter
		export interval=60
		run_monitor $LKP_SRC/monitors/wrapper sched_debug
		unset interval
	}
	
	
	#在这里开启moitor来收集信息
	default_monitors &

	run_setup $LKP_SRC/setup/nr_threads '200%'

	run_setup $LKP_SRC/setup/iterations '5x'

	export duration='5s'
	
	##运行测试用例 即ebizzy
	run_test $LKP_SRC/tests/wrapper ebizzy
	unset duration

	wait
}


# 当测试用例运行完毕以后，/bin/run-local会关闭mointor，然后执行此函数
# 来对mointor输出的结果进行处理变成key:value形式，供后续变成json格式。
extract_stats()
{
	$LKP_SRC/stats/wrapper kmsg
	$LKP_SRC/stats/wrapper uptime
	$LKP_SRC/stats/wrapper iostat
	$LKP_SRC/stats/wrapper vmstat
	$LKP_SRC/stats/wrapper numa-numastat
	$LKP_SRC/stats/wrapper numa-vmstat
	$LKP_SRC/stats/wrapper numa-meminfo
	$LKP_SRC/stats/wrapper proc-vmstat
	$LKP_SRC/stats/wrapper meminfo
	$LKP_SRC/stats/wrapper slabinfo
	$LKP_SRC/stats/wrapper interrupts
	$LKP_SRC/stats/wrapper lock_stat
	$LKP_SRC/stats/wrapper latency_stats
	$LKP_SRC/stats/wrapper softirqs
	$LKP_SRC/stats/wrapper diskstats
	$LKP_SRC/stats/wrapper nfsstat
	$LKP_SRC/stats/wrapper cpuidle
	$LKP_SRC/stats/wrapper turbostat
	$LKP_SRC/stats/wrapper sched_debug
	$LKP_SRC/stats/wrapper ebizzy

	$LKP_SRC/stats/wrapper time ebizzy.time
	$LKP_SRC/stats/wrapper time
	$LKP_SRC/stats/wrapper dmesg
	$LKP_SRC/stats/wrapper kmsg
	$LKP_SRC/stats/wrapper stderr
	$LKP_SRC/stats/wrapper last_state
}

#根据job.sh后面的参数决定执行上面的哪个函数。
"$@"
