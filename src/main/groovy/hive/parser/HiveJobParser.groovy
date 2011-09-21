package hive.parser

import util.MD5

class HiveJobParser {
	def parse(f) {
		def queryMap = [:]
			
		def startTime = 0L
		f.eachLine { line ->
			switch(line) {
				case ~/(?m)^QueryStart\s+.+/ :
					def m = line =~ /(?m)^QueryStart\s+QUERY_STRING="(.+?)"\s+QUERY_ID="(.+?)"\s+TIME="(.+?)"/
					startTime = Long.parseLong(m[0][3])
				    break
				case ~/(?m)^QueryEnd\s+.+/ :
					def m = line =~ /(?m)^QueryEnd\s+.*QUERY_STRING="(.+?)"\s+QUERY_ID="(.+?)"\s+.+TIME="(.+?)"/
					if (m.size() < 1 && m[0].size() < 4)
						break
					printf '         * %s | %10s | %s | %s\n', 
							new java.text.SimpleDateFormat('HH:mm:ss').format(startTime),
	                        Long.parseLong(m[0][3]) - startTime,
							MD5.md5(m[0][1]),
	                        m[0][1].trim().substring(0, Math.min(50, m[0][1].trim().size()))
				    break
				default:
				    break
			}
		}
	}
}