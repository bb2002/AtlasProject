package saintdev.kr.datas

data class TrustData(
    val UUID: String = "UUID",
    val LOCATION: String = "LOCATION",
    val SOURCE: String = "SOURCE",
    val PRICE: Int = 0)

object Trust {
    fun getTrust1() : TrustData {
        return TrustData("AAAAAAAA-BBBB-BBBB-CCCC-CCCCDDDDDDDD", "충북 청주시 흥덕구 옥산면 과학산업3로 29 LG 오산 화학 공장", "U (우라늄)", 36016700)
    }

    fun getTrust2() : TrustData {
        return TrustData("74278BDA-B644-4520-8F0C-720EAF059935", "충북 음성군 삼성면 금일로 955-25 삼성 바이오 음성공장", "Na (나트륨)", 99686760)
    }
}