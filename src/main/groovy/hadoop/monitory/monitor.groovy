//Process p = Runtime.getRuntime().exec('sh -c \"all jps\"')
//Process p = Runtime.getRuntime().exec('all jps')
//p.waitFor()

//def output = """sh -c 'all jps'""".execute().waitFor().text

//def initialSize = 4096
//def os = new ByteArrayOutputStream(initialSize)
//def err = new ByteArrayOutputStream(initialSize)
def p = """all jps""".execute()
//def t = p.consumeProcessOutputStream(os)
//p.waitForProcessOutput()
//t.join()
def output = p.text

def jps = [:]
//BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//String line;
//while ((line = br.readLine()) != null) {
output.eachLine { line ->
	def m = line =~ /^(.*?):\s+(.*?)\s+(.*?)$/
	if (m.size() > 0 && m[0].size() > 3) {
		if (jps[m[0][1]] == null)
			jps[m[0][1]] = []
		if (jps[m[0][1]] =~ /(GroovyStarter|Jps)/)
			return
		jps[m[0][1]] << (m[0][3] == '' ? 'X' : m[0][3])
	}
}

jps.sort({it.key}).each { k, v ->
	printf '%s : %s\n', k, v.sort()
}
