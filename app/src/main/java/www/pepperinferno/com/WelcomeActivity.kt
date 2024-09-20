package www.pepperinferno.com

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.MutableData
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import www.pepperinferno.com.data.FoodProducts

class WelcomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var btnDrinks: Button
    private lateinit var btnOptions: Button

//    private val queryOrder: Query = refFoods.orderByKey()
//    private var foods: MutableList<FoodProducts>? = null
//    private var lstProducts: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()

        btnDrinks = findViewById(R.id.btnDrinks)
        btnOptions = findViewById(R.id.btnOptions)

        btnDrinks.setOnClickListener {
            val intent = Intent(this, DrinksActivity::class.java)
            startActivity(intent)
        }

        btnOptions.setOnClickListener {
            val intent = Intent(this, OptionsActivity::class.java)
            startActivity(intent)
        }

//        initialize()
    }

//    private fun initialize() {
//        foods = ArrayList()
//
//        queryOrder.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                foods!!.clear()
//                for (data in dataSnapshot.children) {
//                    val food: FoodProducts? = data.getValue(FoodProducts::class.java)
//                    food?.key = data.key // Asigna la clave generada por Firebase
//                    if (food != null) {
//                        food!!.add(food)
//                    }
//                }
//                val adapter = AdaptaderFood(
//                    this@PersonasActivity,
//                    personas as ArrayList<Persona>
//                )
//                listaPersonas!!.adapter = adapter
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {}
//        })
//    }
//
//    companion object {
//        private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
//        private val refFoods: DatabaseReference = database.getReference("Pepper Food")
//    }
}