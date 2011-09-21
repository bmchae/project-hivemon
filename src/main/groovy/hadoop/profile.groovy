["/var/log/hadoop", "/usr/share/hadoop/logs"].each { dirname ->
	new File(dirname).eachFileRecurse { f ->
		//if (!f.isDirectory() && f.getPath() =~ /\[?[0-9]+.+sql$/) {
		if (!f.isDirectory() && f.getName() =~ /^hive_job_.*\.txt$/) {
			printf "%15s | %10s | %50s | \n", f.lastModified(), f.size(), f.getName()
		}
	}
}
