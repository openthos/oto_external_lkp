#
#include by testcase.sh
#

do_before()
{
	#add what you want about this testcase

	#launcher
	if [ -f "wallpaper.jpg" ]; then
	  adb -s $androidIP:$port   shell mkdir /sdcard/Pictures/awall
	  adb -s $androidIP:$port   push ./wallpaper.jpg /sdcard/Pictures/awall
	fi

}

