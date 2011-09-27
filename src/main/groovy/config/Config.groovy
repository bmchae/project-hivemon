package config

class Config {
	static IS_LINUX = 'uname -s'.execute().text == 'Linux' ? true : false
 	static HIVE_LOG_DIR = "/tmp/" + System.getProperty('user.name')
	static HADOOP_LOG_DIR = System.getenv()['HADOOP_LOG_DIR'] != null ? System.getenv()['HADOOP_LOG_DIR'] : System.getenv()['HADOOP_HOME'] + '/logs'
}