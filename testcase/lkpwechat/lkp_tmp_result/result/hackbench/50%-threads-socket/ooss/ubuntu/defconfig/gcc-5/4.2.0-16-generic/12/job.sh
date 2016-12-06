#!/bin/sh

export_top_env()
{
	export testcase='hackbench'
	export category='benchmark'
	export enqueue_time='2016-11-10 16:08:21 +0800'
	export testbox='ooss'
	export tbox_group='ooss'
	export kconfig='defconfig'
	export commit='4.2.0-16-generic'
	export rootfs='ubuntu'
	export nr_cpu="$(nproc)"
	export compiler='gcc-5'

	[ -n "$LKP_SRC" ] ||
	export LKP_SRC=/lkp/${user:-lkp}/src
}

run_job()
{
	echo $$ > $TMP/run-job.pid

	. $LKP_SRC/lib/job.sh
	. $LKP_SRC/lib/env.sh

	export_top_env

	default_monitors()
	{
		run_monitor $LKP_SRC/monitors/event/wait 'activate-monitor'
		run_monitor $LKP_SRC/monitors/wrapper ifstat
		export interval=10
		run_monitor $LKP_SRC/monitors/wrapper uptime
		unset interval

		run_monitor $LKP_SRC/monitors/wrapper meminfo

		memory()
		{
			export interval=60
		}
		memory &

		wait
	}
	default_monitors &

	run_setup $LKP_SRC/setup/nr_threads '50%'

	export freq=800
	run_monitor $LKP_SRC/monitors/no-stdout/wrapper perf-profile
	unset freq

	export mode='threads'
	export ipc='socket'
	run_test $LKP_SRC/tests/wrapper hackbench
	unset mode
	unset ipc

	wait
}

extract_stats()
{
	$LKP_SRC/stats/wrapper uptime
	$LKP_SRC/stats/wrapper meminfo
	$LKP_SRC/stats/wrapper perf-profile
	$LKP_SRC/stats/wrapper hackbench

	$LKP_SRC/stats/wrapper time hackbench.time
	$LKP_SRC/stats/wrapper time
	$LKP_SRC/stats/wrapper dmesg
	$LKP_SRC/stats/wrapper kmsg
	$LKP_SRC/stats/wrapper stderr
	$LKP_SRC/stats/wrapper last_state
}

"$@"
