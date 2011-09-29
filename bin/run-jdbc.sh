CP=$HADOOP_HOME/hadoop-0.20.2-core.jar:conf

for i in ${HIVE_HOME}/lib/*.jar ; do
    CP=$CP:$i
done

groovy -cp $CP ~/hivemon/src/main/groovy/jdbc/emp.groovy

