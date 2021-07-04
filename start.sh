#############
##start.sh###
############
cd /home/deploy/demo-mockup
#java -jar test-1.0-SNAPSHOT-jar-with-dependencies.jar &
nohup java -jar test-1.0-SNAPSHOT-jar-with-dependencies.jar > nohup.out 2>&1  &
