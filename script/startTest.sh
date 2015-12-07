RUN_HOME=~/testsdk
for file in ${RUN_HOME}/lib/*.jar;
do CP=${CP}:$file;
done
CP=${CP}:`pwd`/config



java -cp $CP sdk.demo.SDKTest >> $RUN_HOME/log.out &