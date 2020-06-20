package drewcarlson.multibase

import drewcarlson.multibase.Multibase.Base.*

object Multibase {

    enum class Base(val prefix: Char, val alphabet: String) {
        BASE2('0', "01"),
        BASE8('7', "01234567"),
        BASE10('9', "0123456789"),
        BASE16('f', "0123456789abcdef"),
        BASE16_UPPER('F', "0123456789ABCDEF"),
        BASE32('b', "abcdefghijklmnopqrstuvwxyz234567"),
        BASE32_UPPER('B', "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567"),
        BASE32_PAD('c', "abcdefghijklmnopqrstuvwxyz234567="),
        BASE32_PAD_UPPER('C', "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567="),
        BASE32_HEX('v', "0123456789abcdefghijklmnopqrstuvw"),
        BASE32_HEX_UPPER('V', "0123456789ABCDEFGHIJKLMNOPQRSTUVW"),
        BASE32_HEX_PAD('t', "0123456789abcdefghijklmnopqrstuvw="),
        BASE32_HEX_PAD_UPPER('T', "0123456789ABCDEFGHIJKLMNOPQRSTUVW="),
        BASE58_FLICKR('Z', "123456789abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ"),
        BASE58_BTC('z', "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz"),
        BASE64('m', "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"),
        BASE64_URL('u', "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_"),
        BASE64_PAD('M', "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="),
        BASE64_URL_PAD('U', "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_=");

        companion object {

            private val baseMap = HashMap<Char, Base>()

            init {
                for (base in values()) {
                    baseMap[base.prefix] = base
                }
            }

            fun lookup(prefix: Char): Base {
                return baseMap[prefix] ?: error("Unknown Multibase type: $prefix")
            }
        }
    }


    fun encode(base: Base, data: ByteArray): String = when (base) {
        //BASE2 -> base.prefix + String(BinaryCodec().encode(data))
        BASE8 -> base.prefix + BaseN.encode(base.alphabet, BigInt(8), data)
        BASE10 -> base.prefix + BaseN.encode(base.alphabet, BigInt(10), data)
        BASE16 -> base.prefix + BaseN.encode(base.alphabet, BigInt(16), data)
        BASE16_UPPER -> base.prefix + BaseN.encode(base.alphabet, BigInt(16), data)
        BASE32 -> base.prefix + BaseN.encode(base.alphabet, BigInt(32), data)
        BASE32_UPPER -> base.prefix + BaseN.encode(base.alphabet, BigInt(32), data)
        BASE32_PAD -> base.prefix + BaseN.encode(base.alphabet, BigInt(32), data)
        BASE32_PAD_UPPER -> base.prefix + BaseN.encode(base.alphabet, BigInt(32), data)
        BASE32_HEX -> base.prefix + BaseN.encode(base.alphabet, BigInt(32), data)
        BASE32_HEX_UPPER -> base.prefix + BaseN.encode(base.alphabet, BigInt(32), data)
        BASE32_HEX_PAD -> BaseN.encode(base.alphabet, BigInt(32), data)
        BASE32_HEX_PAD_UPPER -> base.prefix + BaseN.encode(base.alphabet, BigInt(32), data)
        BASE58_FLICKR -> base.prefix + BaseN.encode(base.alphabet, BigInt(58), data)
        BASE58_BTC -> base.prefix + BaseN.encode(base.alphabet, BigInt(58), data)
        BASE64 -> base.prefix + BaseN.encode(base.alphabet, BigInt(64), data)
        BASE64_URL -> base.prefix + BaseN.encode(base.alphabet, BigInt(64), data)
        BASE64_PAD -> base.prefix + BaseN.encode(base.alphabet, BigInt(64), data)
        BASE64_URL_PAD -> base.prefix + BaseN.encode(base.alphabet, BigInt(64), data)
        else -> error("${base.name} (${base.prefix}) not implemented")
    }

    fun decode(data: String): ByteArray {
        require(data.length >= 2)
        val rest = data.drop(1)
        return when (val base = Base.lookup(data.first())) {
            //BASE2 -> BinaryCodec().decode(rest.toByteArray())
            BASE8 -> BaseN.decode(base.alphabet, BigInt(8), rest)
            BASE10 -> BaseN.decode(base.alphabet, BigInt(10), rest)
            BASE16 -> BaseN.decode(base.alphabet, BigInt(16), rest)
            BASE16_UPPER -> BaseN.decode(base.alphabet, BigInt(16), rest)
            BASE32 -> BaseN.decode(base.alphabet, BigInt(32), rest)
            BASE32_UPPER -> BaseN.decode(base.alphabet, BigInt(32), rest)
            BASE32_PAD -> BaseN.decode(base.alphabet, BigInt(32), rest)
            BASE32_PAD_UPPER -> BaseN.decode(base.alphabet, BigInt(32), rest)
            BASE32_HEX -> BaseN.decode(base.alphabet, BigInt(32), rest)
            BASE32_HEX_UPPER -> BaseN.decode(base.alphabet, BigInt(32), rest)
            BASE32_HEX_PAD -> BaseN.decode(base.alphabet, BigInt(32), rest)
            BASE32_HEX_PAD_UPPER -> BaseN.decode(base.alphabet, BigInt(32), rest)
            BASE58_FLICKR -> BaseN.decode(base.alphabet, BigInt(58), rest)
            BASE58_BTC -> BaseN.decode(base.alphabet, BigInt(58), rest)
            BASE64 -> BaseN.decode(base.alphabet, BigInt(64), rest)
            BASE64_URL -> BaseN.decode(base.alphabet, BigInt(64), rest)
            BASE64_PAD -> BaseN.decode(base.alphabet, BigInt(64), rest)
            BASE64_URL_PAD -> BaseN.decode(base.alphabet, BigInt(64), rest)
            else -> error("Decoder not found for id (${data.first()})")
        }
    }
}