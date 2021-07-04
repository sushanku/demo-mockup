#############
##stop.sh###
#############
if [ `ps aux | grep "test-1.0-SNAPSHOT-jar-with-dependencies.jar" | grep java | wc -l` -gt 0 ]
then
	echo "Shutting down demo project"
	for pid in `ps aux | grep "test-1.0-SNAPSHOT-jar-with-dependencies.jar" | grep java | awk '{print $2}'`
	do
		kill -9 $pid 2>&1 > /dev/null
	done
	echo "Shutdown Complete"
else
echo "demo project is not running, shutdown skipped"
fi
