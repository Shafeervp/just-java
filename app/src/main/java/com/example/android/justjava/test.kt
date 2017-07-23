///*
// * Copyright (C) 2015 The Android Open Source Project
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package com.example.android.justjava
//
//import android.content.Intent
//import android.net.Uri
//import android.os.Bundle
//import android.support.v7.app.AppCompatActivity
//import android.text.Editable
//import android.view.View
//import android.widget.CheckBox
//import android.widget.EditText
//import android.widget.TextView
//
//import java.text.NumberFormat
//
///**
// * This app displays an order form to order coffee.
// */
//class MainActivity2 : AppCompatActivity() {
//
//    internal var quantity = 2
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
//
//    /**
//     * This method is called when the plus button is clicked.
//     */
//    fun increment(view: View) {
//        if (quantity == 100) {
//            return
//        }
//        quantity =+1
//        displayQuantity(quantity)
//    }
//
//    /**
//     * This method is called when the minus button is clicked.
//     */
//    fun decrement(view: View) {
//        if (quantity == 0) {
//            return
//        }
//        quantity =-1
//        displayQuantity(quantity)
//    }
//
//    /**
//     * This method is called when the order button is clicked.
//     */
//    fun submitOrder(view: View) {
//        // Get user's name
//        val nameField = findViewById(R.id.name_field) as EditText
//        val nameEditable = nameField.text
//        val name = nameEditable.toString()
//
//        // Figure out if the user wants whipped cream topping
//        val whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbox) as CheckBox
//        val hasWhippedCream = whippedCreamCheckBox.isChecked
//
//        // Figure out if the user wants choclate topping
//        val chocolateCheckBox = findViewById(R.id.chocolate_checkbox) as CheckBox
//        val hasChocolate = chocolateCheckBox.isChecked
//
//        // Calculate the price
//        val price = calculatePrice(hasWhippedCream, hasChocolate)
//
//        // Display the order summary on the screen
//        val message = createOrderSummary(name, price, hasWhippedCream, hasChocolate)
//
//        // Use an intent to launch an email app.
//        // Send the order summary in the email body.
//        val intent = Intent(Intent.ACTION_SENDTO)
//        intent.data = Uri.parse("mailto:") // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_SUBJECT,
//                getString(R.string.order_summary_email_subject, name))
//        intent.putExtra(Intent.EXTRA_TEXT, message)
//
//        if (intent.resolveActivity(packageManager) != null) {
//            startActivity(intent)
//        }
//    }
//
//    /**
//     * Calculates the price of the order.
//
//     * @param addWhippedCream is whether or not we should include whipped cream topping in the price
//     * *
//     * @param addChocolate    is whether or not we should include chocolate topping in the price
//     * *
//     * @return total price
//     */
//    private fun calculatePrice(addWhippedCream: Boolean, addChocolate: Boolean): Int {
//        // First calculate the price of one cup of coffee
//        var basePrice = 5
//
//        // If the user wants whipped cream, add $1 per cup
//        if (addWhippedCream) {
//            basePrice = basePrice + 1
//        }
//
//        // If the user wants chocolate, add $2 per cup
//        if (addChocolate) {
//            basePrice = basePrice + 2
//        }
//
//        // Calculate the total order price by multiplying by the quantity
//        return quantity * basePrice
//    }
//
//    /**
//     * Create summary of the order.
//
//     * @param name            on the order
//     * *
//     * @param price           of the order
//     * *
//     * @param addWhippedCream is whether or not to add whipped cream to the coffee
//     * *
//     * @param addChocolate    is whether or not to add chocolate to the coffee
//     * *
//     * @return text summary
//     */
//    private fun createOrderSummary(name: String, price: Int, addWhippedCream: Boolean,
//                                   addChocolate: Boolean): String {
//        var priceMessage = getString(R.string.order_summary_name, name)
//        priceMessage += "\n" + getString(R.string.order_summary_whipped_cream, addWhippedCream)
//        priceMessage += "\n" + getString(R.string.order_summary_chocolate, addChocolate)
//        priceMessage += "\n" + getString(R.string.order_summary_quantity, quantity)
//        priceMessage += "\n" + getString(R.string.order_summary_price,
//                NumberFormat.getCurrencyInstance().format(price.toLong()))
//        priceMessage += "\n" + getString(R.string.thank_you)
//        return priceMessage
//    }
//
//    /**
//     * This method displays the given quantity value on the screen.
//     */
//    private fun displayQuantity(numberOfCoffees: Int) {
//        val quantityTextView = findViewById(
//                R.id.quantity_text_view) as TextView
//       quantityTextView.text
//        quantityTextView.text = "" + numberOfCoffees
//    }
//}
