package drewcarlson.multibase

import com.ionspin.kotlin.bignum.integer.BigInteger

actual typealias PlatformBigInt = BigInteger

internal actual inline class BigInt(private val num: PlatformBigInt) {

    actual constructor(input: Int) : this(BigInteger.fromInt(input))

    actual constructor(magnitude: ByteArray) : this(
        BigInteger.fromUByteArray(magnitude.toUByteArray().toTypedArray())
    )

    actual operator fun plus(other: BigInt): BigInt {
        return BigInt(num + other.num)
    }

    actual operator fun minus(other: BigInt): BigInt {
        return BigInt(num - other.num)
    }

    actual operator fun times(other: BigInt): BigInt {
        return BigInt(num * other.num)
    }

    actual operator fun div(other: BigInt): BigInt {
        return BigInt(num / other.num)
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
        return num.uintValue().toInt()
    }

    actual fun toByteArray(): ByteArray {
        return num.toUByteArray().toUByteArray().toByteArray()
    }

    actual override fun toString(): String {
        return "BigInt(num=$num)"
    }
}