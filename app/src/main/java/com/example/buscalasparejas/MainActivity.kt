package com.example.buscalasparejas

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.Emparejados.R

class MainActivity : AppCompatActivity() {


    //<editor-fold desc="VARIABLES">

    private val imagenesArray = arrayOf(11, 12, 13, 14, 15, 16, 21, 22, 23, 24, 25, 26)

    private var homero = 0
    private var bart = 0
    private var lisa = 0
    private var familia = 0
    private var juntos = 0
    private var comida = 0

    private var turno = 1
    private var puntosj1 = 0
    private var puntosj2 = 0


    private var numeroImagen = 1
    private var escuchar = true

    private lateinit var mp: MediaPlayer
    private lateinit var mpFondo: MediaPlayer

    private lateinit var ib_sonido:ImageButton

    private lateinit var imagen1: ImageView
    private lateinit var imagen2: ImageView

    private lateinit var iv_11:ImageView
    private lateinit var iv_12:ImageView
    private lateinit var iv_13:ImageView
    private lateinit var iv_14:ImageView
    private lateinit var iv_21:ImageView
    private lateinit var iv_22:ImageView
    private lateinit var iv_23:ImageView
    private lateinit var iv_24:ImageView
    private lateinit var iv_31:ImageView
    private lateinit var iv_32:ImageView
    private lateinit var iv_33:ImageView
    private lateinit var iv_34:ImageView

    //</editor-fold>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enlazarGUI()

    }//Fin del onCreate


    private fun enlazarGUI() {

        iv_11 = findViewById(R.id.iv_11)
        iv_12 = findViewById(R.id.iv_12)
        iv_13 = findViewById(R.id.iv_13)
        iv_14 = findViewById(R.id.iv_14)
        iv_21 = findViewById(R.id.iv_21)
        iv_22 = findViewById(R.id.iv_22)
        iv_23 = findViewById(R.id.iv_23)
        iv_24 = findViewById(R.id.iv_24)
        iv_31 = findViewById(R.id.iv_31)
        iv_32 = findViewById(R.id.iv_32)
        iv_33 = findViewById(R.id.iv_33)
        iv_34 = findViewById(R.id.iv_34)


        ib_sonido = findViewById(R.id.ib_sonido)
        ib_sonido.setColorFilter(Color.GREEN)

        sonido("background",true)

        iv_11.tag = "0"
        iv_12.tag = "1"
        iv_13.tag = "2"
        iv_14.tag = "3"
        iv_21.tag = "4"
        iv_22.tag = "5"
        iv_23.tag = "6"
        iv_24.tag = "7"
        iv_31.tag = "8"
        iv_32.tag = "9"
        iv_33.tag = "10"
        iv_34.tag = "11"

        homero = R.drawable.homero
        bart = R.drawable.bart
        comida = R.drawable.comida
        familia = R.drawable.familia
        juntos = R.drawable.juntos
        lisa = R.drawable.lisa

        imagenesArray.shuffle()

        val tv_j1:TextView = findViewById(R.id.tv_j1)
        val tv_j2:TextView = findViewById(R.id.tv_j2)

        tv_j1.setTextColor(Color.WHITE)
        tv_j2.setTextColor(Color.GRAY)


    }//Fin de la funcion enlazarGUI.

    private fun sonido(s: String, loop: Boolean = false) {

        var resID = resources.getIdentifier(s,"raw",packageName)

        if (s == "background"){
            mpFondo = MediaPlayer.create(this,resID)
            mpFondo.isLooping = loop
            mpFondo.setVolume(0.04f, 0.04f)

            if (!mpFondo.isPlaying){
                mpFondo.start()
            }
        }else{
            mp = MediaPlayer.create(this,resID)
            mp.setOnCompletionListener(MediaPlayer.OnCompletionListener {mediaPlayer ->
                mediaPlayer.stop()
                mediaPlayer.release()
            })
            if (!mp.isPlaying){
                mp.start()
            }
        }

    }//Fin de la funcion sonido.

    fun musicaFondo(view: View) {
        if (escuchar) {
            mpFondo.pause()
            ib_sonido.setImageResource(R.drawable.volumen_off)
            ib_sonido.setColorFilter(Color.GRAY)
        } else {
            mpFondo.start()
            ib_sonido.setImageResource(R.drawable.volumen_on)
            ib_sonido.setColorFilter(Color.GREEN)
        }
        escuchar = !escuchar
    }// Fin de la funcion musicaFondo


    fun seleccionar(imagen: View) {
        sonido("touch")
        verificar(imagen)

    }// Fin de la funcion seleccionar


    private fun verificar(imagen: View){
        var iv = (imagen as ImageView)
        var tag = imagen.tag.toString().toInt()

        if(imagenesArray[tag] == 11){
            iv.setImageResource(homero)
        }else if(imagenesArray[tag] == 12){
            iv.setImageResource(bart)
        }else if(imagenesArray[tag] == 13){
            iv.setImageResource(lisa)
        }else if(imagenesArray[tag] == 14){
            iv.setImageResource(familia)
        }else if(imagenesArray[tag] == 15){
            iv.setImageResource(comida)
        }else if(imagenesArray[tag] == 16){
            iv.setImageResource(juntos)
        }else if(imagenesArray[tag] == 21){
            iv.setImageResource(homero)
        }else if(imagenesArray[tag] == 22){
            iv.setImageResource(bart)
        }else if(imagenesArray[tag] == 23){
            iv.setImageResource(lisa)
        }else if(imagenesArray[tag] == 24){
            iv.setImageResource(familia)
        }else if(imagenesArray[tag] == 25){
            iv.setImageResource(comida)
        }else if(imagenesArray[tag] == 26){
            iv.setImageResource(juntos)
        }

        if(numeroImagen == 1){
            imagen1 = iv
            numeroImagen = 2
            iv.isEnabled = false
        } else if(numeroImagen == 2){
            imagen2 = iv
            numeroImagen = 1
            iv.isEnabled = false

            deshabilitarImagenes()
            val h = Handler(Looper.getMainLooper())
            h.postDelayed({sonIguales()},1000)
        }



    }// Fin de la funcion verificar

    private fun sonIguales() {
        if (imagen1.tag == imagen2.tag) {
            // Las imágenes son iguales, realizar acciones correspondientes
            puntosj1++
            val tv_j1: TextView = findViewById(R.id.tv_j1)
            tv_j1.text = "Puntos J1: $puntosj1"

            // Deshabilitar las imágenes seleccionadas
            imagen1.isEnabled = false
            imagen2.isEnabled = false

            // Verificar si se han encontrado todas las parejas
            if (puntosj1 + puntosj2 == 6) {
                // Se han encontrado todas las parejas, realizar acciones correspondientes (fin del juego, reinicio, etc.)
            }
        } else {
            // Las imágenes son diferentes, realizar acciones correspondientes
            val h = Handler(Looper.getMainLooper())
            h.postDelayed({
                // Ocultar las imágenes seleccionadas
                imagen1.setImageResource(R.drawable.oculta)
                imagen2.setImageResource(R.drawable.oculta)

                // Habilitar las imágenes nuevamente
                imagen1.isEnabled = true
                imagen2.isEnabled = true
            }, 1000)
        }
    }



    private fun deshabilitarImagenes(){
        iv_11.isEnabled = false
        iv_12.isEnabled = false
        iv_13.isEnabled = false
        iv_14.isEnabled = false
        iv_21.isEnabled = false
        iv_22.isEnabled = false
        iv_23.isEnabled = false
        iv_24.isEnabled = false
        iv_31.isEnabled = false
        iv_32.isEnabled = false
        iv_33.isEnabled = false
        iv_34.isEnabled = false
    }// Fin de la funcion deshabilitarImagenes



    override fun onDestroy() {
        super.onDestroy()
        mpFondo.release() // Libera el recurso MediaPlayer para el sonido de fondo
        mp.release() // Libera el recurso MediaPlayer para otros sonidos
    }

}//Fin de la Clase MainActivity