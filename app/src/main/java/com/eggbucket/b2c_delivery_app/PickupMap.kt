package com.eggbucket.b2c_delivery_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import android.widget.Button

class PickupMap : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_pickup_map, container, false)


        val viewOrderDetailsButton: Button = view.findViewById(R.id.view_order_details)


        viewOrderDetailsButton.setOnClickListener {

            val navController: NavController = NavHostFragment.findNavController(this)


            navController.navigate(R.id.action_pickupMap_to_orderDetails)


            val bundle = Bundle().apply {
                putString("param1", "value1")
                putString("param2", "value2")
            }
            navController.navigate(R.id.action_pickupMap_to_orderDetails, bundle)
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PickupMap().apply {
                arguments = Bundle().apply {
                    putString("param1", param1)
                    putString("param2", param2)
                }
            }
    }
}
