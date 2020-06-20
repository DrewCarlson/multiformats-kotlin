package drewcarlson.multibase

actual typealias PlatformBigInt = java.math.BigInteger

internal actual inline class BigInt(private val num: PlatformBigInt) {

    actual constructor(input: Int) : this(java.math.BigInteger.valueOf(input.toLong()))

    actual constructor(magnitude: ByteArray) : this(java.math.BigInteger(1, magnitude))

    actual operator fun plus(other: BigInt): BigInt {
        return BigInt(num.add(other.num))
    }

    actual operator fun minus(other: BigInt): BigInt {
        return BigInt(num.subtract(other.num))
    }

    actual operator fun times(other: BigInt): BigInt {
        return BigInt(num.multiply(other.num))
    }

    actual operator fun div(other: BigInt): BigInt {
        return BigInt(num.divide(other.num))
    }

    actual operator fun compareTo(other: BigInt): Int {
        return num.compareTo(other.num)
    }

    actual fun pow(other: Int): BigInt {
        return BigInt(num.pow(other))
    }

    actual fun mod(other: BigInt): BigInt {
        return BigInt(num.mod(other.num))
    }

    actual fun toInt(): Int {
        return num.toInt()
    }

    actual fun toByteArray(): ByteArray {
        return num.toByteArray()
    }
}