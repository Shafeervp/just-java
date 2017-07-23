package com.example.android.justjava

/**
 * Created by shafe on 7/23/2017.
 * Just Java with kotlin
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
import com.example.android.justjava.R.layout.activity_main
import com.example.android.justjava.R.string
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {
    var quantity:Int=2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
    }

    fun increment() : Unit {
        if (quantity==100)
            return
        else {
            quantity++
            displayQuantity(quantity)
        }

    }
fun decrement():Unit{
    if (quantity==0)
        return
    else{
        quantity--
        displayQuantity(quantity)
    }

}
    fun submitOrder() {
        val namefield = findViewById(name_field) as EditText
        val nameditable: Editable = namefield.text
        val name: String = nameditable.toString()
        val choclateCheckBox = findViewById(chocolate_checkbox) as CheckBox
        val choclateHaschecked: Boolean = choclateCheckBox.isChecked
        val whippedCheckBox = findViewById(whipped_cream_checkbox) as CheckBox
        val whippedhaschecked: Boolean = whippedCheckBox.isChecked
        val price: Int = 5
        var total = price

        if (choclateHaschecked) total = +1
        if (whippedhaschecked) total = +2
        val sumTotal = total * quantity

        val finalString = createOrderSummary(name, sumTotal, whippedhaschecked, choclateHaschecked)

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




