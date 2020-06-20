package drewcarlson.multibase

import kotlin.test.Test
import kotlin.test.assertEquals

class MultibaseTest {

    private val encodedSamples = listOf(
        //Multibase.Base.BASE1 to "1",
        //Multibase.Base.BASE2 to "000000001",
        Multibase.Base.BASE8 to "7074432",
        Multibase.Base.BASE10 to "909888",
        Multibase.Base.BASE16 to "f446563656e7472616c697a652065766572797468696e67212121",
        Multibase.Base.BASE16_UPPER to "F446563656E7472616C697A652065766572797468696E67212121",
        Multibase.Base.BASE32 to "birswgzloorzgc3djpjssazlwmvzhs5dinfxgoijbee",
        Multibase.Base.BASE32_UPPER to "BIRSWGZLOORZGC3DJPJSSAZLWMVZHS5DINFXGOIJBEE",
        //Multibase.Base.BASE32_PAD to "cirswgzloorzgc3djpjssazlwmvzhs5dinfxgoijbee======",
        Multibase.Base.BASE32_HEX to "v8him6pbeehp62r39f9ii0pbmclp7it38d5n6e89144",
        Multibase.Base.BASE32_HEX_UPPER to "V8HIM6PBEEHP62R39F9II0PBMCLP7IT38D5N6E89144",
        //Multibase.Base.BASE32_HEX_PAD to "t8him6pbeehp62r39f9ii0pbmclp7it38d5n6e89144======",
        //Multibase.Base.BASE32_HEX_PAD_UPPER to "T8HIM6PBEEHP62R39F9II0PBMCLP7IT38D5N6E89144======",
        Multibase.Base.BASE58_FLICKR to "Z36UQrhJq9fNDS7DiAHM9YXqDHMPfr4EMArvt",
        Multibase.Base.BASE58_BTC to "z36UQrhJq9fNDS7DiAHM9YXqDHMPfr4EMArvt",
        Multibase.Base.BASE64 to "mRGVjZW50cmFsaXplIGV2ZXJ5dGhpbmchISE",
        Multibase.Base.BASE64_URL to "uRGVjZW50cmFsaXplIGV2ZXJ5dGhpbmchISE",
        //Multibase.Base.BASE64_PAD to "MRGVjZW50cmFsaXplIGV2ZXJ5dGhpbmchISE=",
        //Multibase.Base.BASE64_URL_PAD to "URGVjZW50cmFsaXplIGV2ZXJ5dGhpbmchISE="
    )

    @Test
    fun testBase() {
        encodedSamples.forEach { (base, input) ->
            val origin = Multibase.decode(input)
            val encode = Multibase.encode(base, origin)
            assertEquals(input, encode, "Encoding: ${base.name}")
        }
    }

    @Test
    fun testDecodeToBigInteger() {
        val bi = BaseN.decodeToBigInteger("abcdefghijklmnopqrstuvwxyz234567=", BigInt(32), "===")
        assertEquals("BigInt(num=33824)", bi.toString())
    }
}
