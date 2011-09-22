package config

class Config {
	static HIVE_LOG_DIR = "/tmp/" + System.getProperty('user.name')
	//static HADOOP_LOG_DIR = System.getenv()['HADOOP_HOME'] + '/logs'
	static HADOOP_LOG_DIR = '/var/log/hadoop'
}