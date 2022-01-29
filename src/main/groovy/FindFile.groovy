import static groovy.io.FileType.FILES

def directoryName = "/Users/owner/Downloads/Dropbox"
int count = 0;
def fileSubStr = ".gradle"

def filePattern = ~/${fileSubStr}/

def directory = new File(directoryName)


if (!directory.isDirectory())
{
   println "The provided directory name ${directoryName} is NOT a directory."
   System.exit(-2)
}

println "Searching for files including ${fileSubStr} in directory ${directoryName}..."

def wanderClosure =
{
   new File(directoryName).eachFileRecurse(FILES) 
   {
      if(it.name.endsWith('.groovy')) 
      {
        println it
      } // end of if
   } // end of File
} // end of wander

def findFilenameClosure =
{
   if (filePattern.matcher(it.name).find())
   {
      println "\nLooking at  ${it} ( size: ${it.size()} bytes ) ..."
      //it.delete();
      count++;
      println "       .... ${it} - found"
   }
} // end of logic

println "Matching Files:"
directory.eachFileRecurse(findFilenameClosure)
if (count>0) {println "\n ----- ${count} files were removed"}

println "\n-------------------\n... file name ends with Groovy ? "
wanderClosure()

println "-------- the end --------"