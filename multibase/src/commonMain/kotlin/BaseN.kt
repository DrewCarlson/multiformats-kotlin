package drewcarlson.multibase

internal object BaseN {

    fun decode(alphabet: String, base: BigInt, input: String): ByteArray {
        val bytes = decodeToBigInteger(alphabet, base, input).toByteArray()
        val stripSignByte = bytes.size > 1 && bytes[0].compareTo(0) == 0 && bytes[1] < 0
        var leadingZeros = 0
        var i = 0
        while (input[i] == alphabet[0]) {
            leadingZeros++
            i++
        }
        val tmp = ByteArray(bytes.size - (if (stripSignByte) 1 else 0) + leadingZeros)
        bytes.copyInto(tmp, leadingZeros, if (stripSignByte) 1 else 0, tmp.size - leadingZeros)
        return tmp
    }

    fun encode(alphabet: String, base: BigInt, input: ByteArray): String {
        var bi = BigInt(input)
        return buildString {
            while (bi >= base) {
                val mod = bi.mod(base)
                insert(0, alphabet[mod.toInt()])
                bi = bi.minus(mod).div(base)
            }
            insert(0, alphabet[bi.toInt()])
            //convert leading zeros.
            input
                .takeWhile { it.compareTo(0) == 0 }
                .forEach { _ -> insert(0, alphabet[0]) }
        }
    }

    fun decodeToBigInteger(alphabet: String, base: BigInt, input: String): BigInt {
        return input.foldIndexed(BigInt(0)) { i, acc, char ->
            val alphaIndex = alphabet.indexOf(char)
            check(alphaIndex >= 0) { "Invalid character $char at $i" }
            acc + BigInt(alphaIndex) * base.pow(input.lastIndex - i)
        }
    }
}

