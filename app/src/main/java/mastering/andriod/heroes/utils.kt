package mastering.andriod.heroes

import mastering.andriod.heroes.models.Hero
import mastering.andriod.heroes.models.HeroResponse
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun generateHash(): String {
    val md5 = try {
        MessageDigest.getInstance("MD5")
    } catch (e: NoSuchAlgorithmException) {
        throw RuntimeException("MD5 algorithm not found", e)
    }

    val timestamp = System.currentTimeMillis().toString()
    val input = "$timestamp${BuildConfig.MARVEL_PRIVATE_KEY}${BuildConfig.MARVEL_PUBLIC_KEY}"
    val digest = md5.digest(input.toByteArray())

    val result = StringBuilder()
    for (byte in digest) {
        result.append(String.format("%02x", byte))
    }

    return result.toString()
}

fun parseHero(heroResponse: HeroResponse) = Hero(
    id = heroResponse.id,
    name = heroResponse.name,
    description = heroResponse.description,
    imageUrl = heroResponse.thumbnail.getFullImageUrl()
)

