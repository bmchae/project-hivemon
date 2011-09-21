package util

class MD5 {
	def static md5(String plain) {
		java.security.MessageDigest digest = java.security.MessageDigest.getInstance("MD5")
		digest.update(plain.bytes)
		BigInteger big = new BigInteger(1,digest.digest())
		return big.toString(16).padLeft(32,"0")
	}
}