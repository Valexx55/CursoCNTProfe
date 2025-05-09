    package edu.cnt.developer.profe

    import android.content.Intent
    import android.os.Bundle
    import android.util.Log
    import android.view.View
    import android.widget.EditText
    import android.widget.TextView
    import android.widget.Toast
    import androidx.activity.enableEdgeToEdge
    import androidx.appcompat.app.AppCompatActivity
    import androidx.core.view.ViewCompat
    import androidx.core.view.WindowInsetsCompat
    import kotlin.random.Random

    class Adivina : AppCompatActivity() {
        var posibilidades: Int = 5
        var numeroAleatorio: Int = 0

        lateinit var numeroCampo: EditText
        lateinit var campoPosibilidades: TextView
        lateinit var campoResultado: TextView

        /**
         * var numeroCampo: EditText = findViewById<EditText>(R.id.txt_numero)
         *             var numero: Int = Integer.parseInt(numeroCampo.text.toString())
         *
         *             var campoPosibilidades: TextView = findViewById<TextView>(R.id.txt_posibilidades)
         *             var campoResultado: TextView = findViewById<TextView>(R.id.txt_mensaxe)
         */


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            Log.d("MIAPP", "ONCREATE")
            enableEdgeToEdge()
            setContentView(R.layout.activity_adivina)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            this.numeroCampo= findViewById<EditText>(R.id.txt_numero) //mete persona
            this.campoPosibilidades = findViewById<TextView>(R.id.txt_posibilidades) //vidas
            this.campoResultado = findViewById<TextView>(R.id.txt_mensaxe) //mensaje mayor/menor

            //numeroAleatorio = Random.nextInt(1, 100);
            //outState.putInt("NUM_ALEATORIO", this.numeroAleatorio)
            //outState.putInt("NUM_POSIBILIDADES", this.posibilidades)
            this.numeroAleatorio = savedInstanceState?.getInt("NUM_ALEATORIO") ?: Random.nextInt(1, 100)
            this.posibilidades = savedInstanceState?.getInt("NUM_POSIBILIDADES") ?: 5
            Log.d("Dato", "numero $numeroAleatorio")
            this.campoPosibilidades.text = this.posibilidades.toString()


        }

        //TODO mejorar: 1) opción de reiniciar una vez finalizada la partida
        //2) si pierde, informar al usuario del número buscado/aleatorio

        override fun onStart() {
            super.onStart()
            Log.d("MIAPP", "ONSTART")
        }

        override fun onResume() {
            super.onResume()
            Log.d("MIAPP", "ONRESUME")
        }

        override fun onPause() {
            super.onPause()
            Log.d("MIAPP", "ONPAUSE")
        }

        override fun onStop() {
            super.onStop()
            Log.d("MIAPP", "ONSTOP")
        }

        override fun onDestroy() {
            super.onDestroy()
            Log.d("MIAPP", "ONDESTROY")
        }

        override fun onSaveInstanceState(outState: Bundle) {
            super.onSaveInstanceState(outState)
            //ESTE MÉTODO ES INVOCADO POR ANDROID AUTOMÁTICAMENTE CUANDO SE RECREA LA ACTIVIDAD
            //CUANDO GIRO EL TELÉFONO TAMBIÉN
            //ME DA LA OPORTUNIDAD DE GUARDAR INFORMACIÓN NO VISUAL
            Log.d("MIAPP", "onSaveInstanceState")
            //vamos a la guardar la info en el bundle - ESTADO
            //las vidas-posibilidades
            //el número aleatorio
            outState.putInt("NUM_ALEATORIO", this.numeroAleatorio)
            outState.putInt("NUM_POSIBILIDADES", this.posibilidades)

        }



        fun testar(view: View): Unit {

            var numeroCampo: EditText = findViewById<EditText>(R.id.txt_numero)
            var numero: Int = Integer.parseInt(numeroCampo.text.toString())

            var campoPosibilidades: TextView = findViewById<TextView>(R.id.txt_posibilidades)
            var campoResultado: TextView = findViewById<TextView>(R.id.txt_mensaxe)

            if (this.numeroAleatorio == numero) {
                campoResultado.text = "Correcto"
                //var pantallaCorrecto: Intent = Intent(this, adivinanzaCorrecto::class.java)
                //startActivity(pantallaCorrecto)
                var final: Toast =
                    Toast.makeText(this, "Has acertado campeón, ENHORABUENA!", Toast.LENGTH_LONG)
                final.show()

            } else {

                if (numeroAleatorio > numero) {
                    campoResultado.text = "Erro... O número é maior"
                } else {
                    campoResultado.text = "Erro... O número é menor"

                }

                this.posibilidades--
                campoPosibilidades.text = "Posibilidades = ${this.posibilidades}"

                if (this.posibilidades == 0) {

                    numeroCampo.isEnabled = false;
                    var final: Toast =
                        Toast.makeText(this, "Xa non podes seguir xogando", Toast.LENGTH_LONG)
                    final.show()

                }

            }
        }
    }