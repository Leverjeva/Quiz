package com.example.skillbox_hw_quiz.ui.main

import android.animation.ObjectAnimator
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.FragmentQuestionBinding
import com.example.skillbox_hw_quiz.quiz.QuizStorage
import java.util.*

class QuestionFragment : Fragment() {

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!

    private val deviceLocal: String = Locale.getDefault().language
    private val locale: QuizStorage.Locale =
        if (deviceLocal == "ru") QuizStorage.Locale.Ru else QuizStorage.Locale.En


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var answer1 = -1
        var answer2 = -1
        var answer3 = -1

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_questionFragment_to_startFragment)
        }

        binding.firstQuestion.text = QuizStorage.getQuiz(locale).questions[0].question
        binding.secondQuestion.text = QuizStorage.getQuiz(locale).questions[1].question
        binding.thirdQuestion.text = QuizStorage.getQuiz(locale).questions[2].question

        ObjectAnimator.ofFloat(binding.firstQuestion, View.ALPHA, 0f, 1f).apply {
            duration = 2500
            repeatCount = 0
            start()
        }

        ObjectAnimator.ofFloat(binding.secondQuestion, View.ALPHA, 0f, 1f).apply {
            duration = 2500
            repeatCount = 0
            start()
        }

        ObjectAnimator.ofFloat(binding.thirdQuestion, View.ALPHA, 0f, 1f).apply {
            duration = 2500
            repeatCount = 0
            start()
        }

        binding.radioButton11.text = QuizStorage.getQuiz(locale).questions[0].answers[0]
        binding.radioButton12.text = QuizStorage.getQuiz(locale).questions[0].answers[1]
        binding.radioButton13.text = QuizStorage.getQuiz(locale).questions[0].answers[2]
        binding.radioButton14.text = QuizStorage.getQuiz(locale).questions[0].answers[3]

        binding.radioButton21.text = QuizStorage.getQuiz(locale).questions[1].answers[0]
        binding.radioButton22.text = QuizStorage.getQuiz(locale).questions[1].answers[1]
        binding.radioButton23.text = QuizStorage.getQuiz(locale).questions[1].answers[2]
        binding.radioButton24.text = QuizStorage.getQuiz(locale).questions[1].answers[3]

        binding.radioButton31.text = QuizStorage.getQuiz(locale).questions[2].answers[0]
        binding.radioButton32.text = QuizStorage.getQuiz(locale).questions[2].answers[1]
        binding.radioButton33.text = QuizStorage.getQuiz(locale).questions[2].answers[2]
        binding.radioButton34.text = QuizStorage.getQuiz(locale).questions[2].answers[3]

        binding.firstAnswers.setOnCheckedChangeListener { group, _ ->
            answer1 = group.getCheckedRadioButtonPosition()
            if (answer1 == 0 || answer1 == 1) {
                MediaPlayer.create(activity, R.raw.wrong_answer).start()
            } else MediaPlayer.create(activity, R.raw.right_answer).start()
        }

        binding.secondAnswers.setOnCheckedChangeListener { group, _ ->
            answer2 = group.getCheckedRadioButtonPosition()
            if (answer2 == 0 || answer2 == 1) {
                MediaPlayer.create(activity, R.raw.right_answer).start()
            } else MediaPlayer.create(activity, R.raw.wrong_answer).start()
        }

        binding.thirdAnswers.setOnCheckedChangeListener { group, _ ->
            answer3 = group.getCheckedRadioButtonPosition()
            if (answer3 == 0 || answer3 == 1) {
                MediaPlayer.create(activity, R.raw.right_answer).start()
            } else MediaPlayer.create(activity, R.raw.wrong_answer).start()
        }

        binding.sendButton.setOnClickListener {
            if (answer1 == -1 || answer2 == -1 || answer3 == -1) {
                Toast.makeText(activity, getString(R.string.answer), Toast.LENGTH_LONG).show()
            } else {
             //   val bundle = bundleOf("answer1" to answer1, "answer2" to answer2, "answer3" to answer3)
                 val action = QuestionFragmentDirections.actionQuestionFragmentToResultFragment(
                 answer1, answer2, answer3
                 )
              //  findNavController().navigate(R.id.action_questionFragment_to_resultFragment, bundle)
              findNavController().navigate(action)
            }
        }
    }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

        private fun RadioGroup.getCheckedRadioButtonPosition(): Int {
            val radioButtonId = checkedRadioButtonId
            return children.mapIndexed { index: Int, view: View ->
                index to view
            }.firstOrNull {
                it.second.id == radioButtonId
            }?.first ?: -1
        }

    }
