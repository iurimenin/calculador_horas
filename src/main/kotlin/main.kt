import java.text.SimpleDateFormat
import java.util.*

val sdf = SimpleDateFormat("HH:mm:ss")

fun main() {

    println("O que deseja? (Digite a opção. Ex: 1)")
    println("1 - Calcular hora de sair")
    println("2 - Calcular hora extra")

    val stringInput = readLine()

    if (stringInput?.toInt() == 1) {
        println("Hora chegada:")
        val horaChegada = readLine()

        println("Hora saída:")
        val horaSaida = readLine()

        println("Hora Retorno:")
        val horaRetorno = readLine()
        
        val dateChegada = Calendar.getInstance()
        val dateSaida = Calendar.getInstance()
        val dateRetorno = Calendar.getInstance()

        dateChegada.set(Calendar.HOUR_OF_DAY, horaChegada?.split(":")?.get(0)?.toInt() ?: 0)
        dateChegada.set(Calendar.MINUTE, horaChegada?.split(":")?.get(1)?.toInt() ?: 0)
        dateChegada.set(Calendar.SECOND, 0)
        dateChegada.set(Calendar.MILLISECOND, 0)

        dateSaida.set(Calendar.HOUR_OF_DAY, horaSaida?.split(":")?.get(0)?.toInt() ?: 0)
        dateSaida.set(Calendar.MINUTE, horaSaida?.split(":")?.get(1)?.toInt() ?: 0)
        dateSaida.set(Calendar.SECOND, 0)
        dateSaida.set(Calendar.MILLISECOND, 0)

        dateRetorno.set(Calendar.HOUR_OF_DAY, horaRetorno?.split(":")?.get(0)?.toInt() ?: 0)
        dateRetorno.set(Calendar.MINUTE, horaRetorno?.split(":")?.get(1)?.toInt() ?: 0)
        dateRetorno.set(Calendar.SECOND, 0)
        dateRetorno.set(Calendar.MILLISECOND, 0)

        val horaManha = dateSaida.timeInMillis - dateChegada.timeInMillis

        val millisecondToFinish = (28800000 - horaManha)

        dateRetorno.timeInMillis = dateRetorno.timeInMillis + millisecondToFinish

        println("Você pode sair ás: ${sdf.format(dateRetorno.time)}")

        println("Deseja calcular com hora extra? (S ou N)")

        val sn = readLine()

        if (sn?.toUpperCase() == "S") {
            calcularHoraExtra(sdf.format(dateRetorno.time))
        }

    } else if (stringInput?.toInt() == 2) {

        println("Que hora você pode sair?: ")
        
        calcularHoraExtra(readLine())
    }
}

fun calcularHoraExtra(horaSair: String?) {

    println("Quantas horas extras você quer fazer?")

    val horasExtras = readLine()

    println("Quer fazer minutos extras também? (S/N)")

    val sn = readLine()

    val minutosExtras = if (sn?.toUpperCase() == "S") {
        println("Quantos minutos extras você quer fazer?")
        readLine()?.toIntOrNull()
    } else {
        0
    }

    val dateRetorno = Calendar.getInstance()
    dateRetorno.set(Calendar.HOUR_OF_DAY, horaSair?.split(":")?.get(0)?.toInt() ?: 0)
    dateRetorno.set(Calendar.MINUTE, horaSair?.split(":")?.get(1)?.toInt() ?: 0)
    dateRetorno.set(Calendar.SECOND, 0)
    dateRetorno.set(Calendar.MILLISECOND, 0)

    dateRetorno.add(Calendar.HOUR_OF_DAY, horasExtras?.toIntOrNull() ?: 0)
    dateRetorno.add(Calendar.MINUTE, minutosExtras ?: 0)

    if (minutosExtras ?:0 > 0) {
        println("Você deve sair ás: ${sdf.format(dateRetorno.time)} para fazer $horasExtras hora(s) com $minutosExtras minutos extra(s).")
    } else {
        println("Você deve sair ás: ${sdf.format(dateRetorno.time)} para fazer $horasExtras hora(s) extra(s).")
    }
}
