#!/usr/bin/env groovy

def dir_name = '.'
def keyword  = ''

if (args.length == 0) {
    println 'Usage : jarfind [dir] [keyword]'
} else if (args.length == 1) {
	keyword = args[0]
} else {
	dir_name = args[0]
	keyword = args[1]
}

def dir = new File(dir_name);
def jarContents;
dir.eachFileRecurse{file ->
   if (file.name =~ /.*\.jar$/) {
       println "* ${file.canonicalPath}:";

       jarContents = "jar tvf ${file}".execute().text;
	   jarContents.eachLine{line ->
	      if (line.contains(keyword)){
	          println '    ' + line.split(/\s+/).last()
	      }
	   }
	}
}
