package com.example.skillbox_hw_quiz.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.FragmentResultsBinding
import com.example.skillbox_hw_quiz.quiz.QuizStorage
import java.util.*

class ResultsFragment : Fragment() {

//    private var answer1: Int? = null
//    private var answer2: Int? = null
//    private var answer3: Int? = null

    private var _binding: FragmentResultsBinding? = null
    private val binding get() = _binding!!

    private val deviceLocal: String = Locale.getDefault().language
    private val locale:QuizStorage.Locale = if (deviceLocal == "ru") QuizStorage.Locale.Ru else QuizStorage.Locale.En

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // arguments?.let {
       //     answer1 = it.getInt("answer1")
       //     answer2 = it.getInt("answer2")
       //     answer3 = it.getInt("answer3")
       // }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultsBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       val answer: ResultsFragmentArgs by navArgs()

        binding.feedback1.text = QuizStorage.getQuiz(locale).questions[0].feedback[answer.answer1]
        binding.feedback2.text =QuizStorage.getQuiz(locale).questions[1].feedback[answer.answer2]
        binding.feedback3.text =QuizStorage.getQuiz(locale).questions[2].feedback[answer.answer3]

        binding.textView.animate().apply {
            rotation(360f)
            duration = 2000
            start()
        }

        binding.startOver.animate().apply {
            rotationX(360f)
            duration = 2000
            start()
        }

        binding.startOver.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_questionFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}