package drewcarlson.multibase

expect class PlatformBigInt

internal expect inline class BigInt(private val num: PlatformBigInt) {

    constructor(input: Int)
    constructor(magnitude: ByteArray)

    operator fun plus(other: BigInt): BigInt
    operator fun minus(other: BigInt): BigInt
    operator fun times(other: BigInt): BigInt
    operator fun div(other: BigInt): BigInt

    operator fun compareTo(other: BigInt): Int

    fun pow(other: Int): BigInt
    fun mod(other: BigInt): BigInt

    fun toInt(): Int
    fun toByteArray(): ByteArray

    override fun toString(): String
}