package jp.hanamakinau.usefultools.domain.domainobject.randomteaming

data class RandomTeamingSetting(
    val name: String,
    val valueType: ValueType,
    val logicType: RandomTeamingLogic.LogicType,
) {
    sealed interface ValueType {

        fun defaultValue(): Any

        fun getValueOrDefault(value: Any): Any

        data object Int : ValueType {
            override fun defaultValue() = 0
            override fun getValueOrDefault(value: Any) = try {
                value.toString().toInt()
            } catch (e: Exception) {
                defaultValue()
            }
        }
    }
}