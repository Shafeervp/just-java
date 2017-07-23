package com.example.android.justjava

/**
 * Created by shafe on 7/23/2017.
 * Just Java with kotlin
 * Kotlin Version for the Udacity program Just-Java from Android for Beginners
 */

import android.content.Intent
import android.content.Intent.*
import android.net.Uri.parse
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.example.android.justjava.R.id.*
import com.example.android.justjava.R.layout
import com.example.android.justjava.R.string
import java.text.NumberFormat

//Kotlin Class declaration notice the use of ':' for extend
class MainActivity : AppCompatActivity() {
    var quantity:Int=2
//Use keyword 'fun' for creatting a method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
    }
/*
*This method is called when + button is pressed
 */
    fun increment() : Unit {
        if (quantity==100)
            return
        else {
            quantity++
            displayQuantity(quantity)
        }

    }
    /*
    *This Method is called When - Button is clicked
     */
fun decrement():Unit{
    if (quantity==0)
        return
    else{
        quantity--
        displayQuantity(quantity)
    }

}
    /*
     * This method is called when Order Button pressed

     * Calculates the price of the order.

     * @param addWhippedCream is whether or not we should include whipped cream topping in the price
     * *
     * @param addChocolate    is whether or not we should include chocolate topping in the price
     * *
     * @return finalString
     */

    fun submitOrder() {
        val namefield = findViewById(name_field) as EditText
        val nameditable: Editable = namefield.text
        val name: String = nameditable.toString()
        val addChoclate = findViewById(chocolate_checkbox) as CheckBox
        val choclateHaschecked: Boolean = addChoclate.isChecked
        val addWhippedCreaam: CheckBox = findViewById(whipped_cream_checkbox) as CheckBox
        val whippedhaschecked: Boolean = addWhippedCreaam.isChecked
        val price: Int = 5
        var total = price

        if (choclateHaschecked) total = +1
        if (whippedhaschecked) total = +2
        val sumTotal = total * quantity

        val finalString = createOrderSummary(name, sumTotal, whippedhaschecked, choclateHaschecked)
        //Creating an Intent for sending into Mail
        val intent = Intent(ACTION_SENDTO)
        intent.data = parse("mailto:")
        with(receiver = intent) {
            putExtra(EXTRA_SUBJECT,
                getString(string.order_summary_email_subject, name))
            putExtra(EXTRA_TEXT, finalString)
        }

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)

        }
    }

    //abstract fun createOrderSummary()

   private fun createOrderSummary(name: String, price: Int, addWhippedCream: Boolean,
                                   addChocolate: Boolean): String {
        var priceMessage = getString(string.order_summary_name, name)
        priceMessage += "\n" + getString(string.order_summary_whipped_cream, addWhippedCream)
        priceMessage += "\n" + getString(string.order_summary_chocolate, addChocolate)
        priceMessage += "\n" + getString(string.order_summary_quantity, quantity)
        priceMessage += "\n" + getString(string.order_summary_price,
                NumberFormat.getCurrencyInstance().format(price.toLong()))
        priceMessage += "\n" + getString(string.thank_you)
        return priceMessage
    }

    private fun displayQuantity(quantity: Int) {
        val quantityTextView = findViewById(quantity_text_view) as TextView
        quantityTextView.text=" " +quantity

    }

}




