package util


class MD5 {
	def static md5(String plain) {
		java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5")
		md.update(plain.bytes)
		BigInteger big = new BigInteger(1, md.digest())
		return big.toString(16).padLeft(32,"0")
	}
}