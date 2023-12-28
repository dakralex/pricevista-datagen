import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.multiple
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.boolean
import com.github.ajalt.clikt.parameters.types.file

class Main : CliktCommand(name = "datagen") {
    override fun run() = Unit
}

class Init : CliktCommand(help = "Initialize the database") {
    override fun run() {
        TODO("Not yet implemented")
    }
}

class Update : CliktCommand(help = "Update the database") {
    val verbose by option().boolean()
    val files by argument().file(
        mustExist = true,
        canBeFile = true,
        canBeDir = true,
        mustBeReadable = false,
        mustBeWritable = false
    ).multiple()

    override fun run() {
        TODO("Not yet implemented")
    }
}

class Drop : CliktCommand(help = "Drop the database") {
    val verbose by option().boolean()
    override fun run() {
        TODO("Not yet implemented")
    }
}

fun main(args: Array<String>) =
    Main().subcommands(Init(), Update(), Drop()).main(args)