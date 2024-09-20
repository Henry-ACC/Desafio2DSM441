package www.pepperinferno.com.data

data class FoodProducts(
    var name: String? = null,
    var price: Double? = null,
    var key: String? = null, // Usado como clave generada por Firebase
    var foo: MutableMap<String, Boolean> = mutableMapOf()
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "price" to price,
            "foo" to foo
        )
    }
}