package com.cropbit.home_module.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cropbit.databinding.FragmentBitBotBinding
import com.cropbit.home_module.presentation.adapter.MessageAdapter
import com.cropbit.home_module.presentation.view_model.BitBotViewModel
import com.cropbit.utils.models.bot_bit_feature.Message
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class BitBotFragment : Fragment() {
    private lateinit var binding: FragmentBitBotBinding
    private var messageList: MutableList<Message> = mutableListOf()
    private lateinit var messageAdapter: MessageAdapter

    private val bitBotViewModel: BitBotViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBitBotBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupUi()
        setUpObservers()
    }

    private fun setUpObservers() {
        addToChat("Hi, BitBot Here\nHow Can I Help You?", Message.SENT_BY_BOT)


        bitBotViewModel.response.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                addResponse(it.trim())
            }
        }
    }

    private fun setupUi() {

        messageAdapter = MessageAdapter(messageList)
        binding.recyclerView.adapter = messageAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.sendBtn.setOnClickListener {
            runBlocking {
                addToChat(binding.messageEditText.text?.trim().toString(), Message.SENT_BY_ME)
                bitBotViewModel.fetchResponse(binding.messageEditText.text?.trim().toString())
                binding.messageEditText.setText("")
                addToChat("Typing...", Message.SENT_BY_BOT)
            }
        }
    }


    private fun addToChat(message: String, sentBy: String) {
        lifecycleScope.launch {
            messageList.add(Message(message, sentBy))
            messageAdapter.notifyDataSetChanged()
            binding.recyclerView.smoothScrollToPosition(messageAdapter.itemCount)
        }
    }

    private fun addResponse(response: String) {
        messageList.removeAt(messageList.size - 1)
        addToChat(response, Message.SENT_BY_BOT)
    }
}