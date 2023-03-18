package com.example.greencycle

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.greencycle.databinding.Fragment6Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Fragment6 : Fragment() {


    lateinit var binding: Fragment6Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment6Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = requireActivity().getSharedPreferences("user_db", Context.MODE_PRIVATE)
        var id=pref.getInt("idUser",0)
        getOrderWaiting(id)
        binding.waitingP.setOnClickListener { view: View ->
            binding.waitingP.setBackgroundColor(Color.parseColor("#9DD549"))
            binding.waitingP.setTextColor(Color.parseColor("#FFFFFF"))
            binding.acceptedP.setBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.acceptedP.setTextColor(Color.parseColor("#000000"))
            getOrderWaiting(id)

        }
        binding.acceptedP.setOnClickListener { view: View ->

            binding.acceptedP.setBackgroundColor(Color.parseColor("#9DD549"))
            binding.acceptedP.setTextColor(Color.parseColor("#FFFFFF"))
            binding.waitingP.setBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.waitingP.setTextColor(Color.parseColor("#000000"))
            getOrderAccepted(id)

        }

    }
    private fun getOrderWaiting(id:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val response =RetrofitService.endpoint.getOrderWaiting(id)
            withContext(Dispatchers.Main) {
                // binding.progressBar.visibility= View.INVISIBLE
                if (response.isSuccessful) {
                    val announcements = response.body()
                    if (announcements != null) {
                        binding.recycler2.layoutManager = GridLayoutManager(
                            requireActivity(),
                            resources.getInteger(R.integer.col)
                        )
                        binding.recycler2.adapter = OrdersRefusedAdapter(requireActivity(), announcements!!)
                    }
                    //else {view?.findNavController()?.navigate(R.id.action_fragment2_to_noreservation)}
                }
                else {
                    Toast.makeText(requireActivity(),"une erreur", Toast.LENGTH_SHORT).show()}
            }

        }
    }

    private fun getOrderAccepted(id:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val response =RetrofitService.endpoint.getOrderAccepted(id)
            withContext(Dispatchers.Main) {
                // binding.progressBar.visibility= View.INVISIBLE
                if (response.isSuccessful) {
                    val announcements = response.body()
                    if (announcements != null) {
                        binding.recycler2.layoutManager = GridLayoutManager(
                            requireActivity(),
                            resources.getInteger(R.integer.col)
                        )
                        binding.recycler2.adapter = OrdersAcceptedAdapter(requireActivity(), announcements!!)
                    }
                    //else {view?.findNavController()?.navigate(R.id.action_fragment2_to_noreservation)}
                }
                else {
                    Toast.makeText(requireActivity(),"une erreur", Toast.LENGTH_SHORT).show()}
            }

        }
    }
}