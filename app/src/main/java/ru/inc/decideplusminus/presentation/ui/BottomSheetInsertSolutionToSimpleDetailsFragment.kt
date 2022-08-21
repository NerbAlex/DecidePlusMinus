package ru.inc.decideplusminus.presentation.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.MotionEvent
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.inc.decideplusminus.R
import ru.inc.decideplusminus.databinding.FragmentBottomSheetInsertSolutionBinding
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseBottomSheetFragment
import ru.inc.decideplusminus.presentation.ui.events.UiEvent
import ru.inc.decideplusminus.presentation.view_model.simple.insert_to_simple.InsertSolutionToSimpleDetailsVM
import ru.inc.decideplusminus.presentation.view_model.simple.insert_to_simple.InsertSolutionToSimpleDetailsViewState
import java.util.*

class BottomSheetInsertSolutionToSimpleDetailsFragment :
    BaseBottomSheetFragment<FragmentBottomSheetInsertSolutionBinding, InsertSolutionToSimpleDetailsViewState>(
        FragmentBottomSheetInsertSolutionBinding::inflate
    ) {

    private var viewModel: InsertSolutionToSimpleDetailsVM? = null
    private val navArgs: BottomSheetInsertSolutionToSimpleDetailsFragmentArgs by navArgs()
    private var parentId: Long? = null
    private var parentName: String? = null
    private var argumentLvl: Int = 2
    private val RecordAudioRequestCode = 777
    private var speechRecognizer: SpeechRecognizer? = null
    private var intentRecognizer: Intent? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        MyApp.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer?.destroy()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getArgs()
        viewModel = initViewModel()
        initSpeechRecognizer()

        // TODO При дизайне закруглить диалог

        // TODO скрывать клавиатуру, рассмотреть все кейсы с микрофоном и плюс/минусом

        updateView {
            mic.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    when (event?.actionMasked) {
                        MotionEvent.ACTION_DOWN -> {
                            mic.setCardBackgroundColor(resources.getColor(R.color.red))
                            checkPermissionRecord()
                            speechRecognizer?.startListening(intentRecognizer)
                        }
                        MotionEvent.ACTION_UP -> {
                            mic.setCardBackgroundColor(resources.getColor(R.color.success_light)) // todo loader
                            speechRecognizer?.stopListening()
                        }
                    }
                    return false
                }

            })
        }

        updateView {

        }

        binding.mic.setOnClickListener {

        }

        binding.minus.setOnClickListener {
            val text = binding.editText.text.trim().toString()
            if (text.isNotEmpty()) viewModel?.minus(parentId, argumentLvl, text)
        }

        binding.plus.setOnClickListener {
            val text = binding.editText.text.trim().toString()
            if (text.isNotEmpty()) viewModel?.plus(parentId, argumentLvl, text)
        }
    }

    private fun initSpeechRecognizer() { // todo сделать by lazy speechRecognizer и intentRecognizer если тяжело по перформансу
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireActivity())
        intentRecognizer = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intentRecognizer?.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intentRecognizer?.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

        speechRecognizer?.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {}
            override fun onError(error: Int) {}
            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (matches != null) {
                    val resultText = matches[0]
                    val status = chekTextStatus(resultText) ?: return
                    updateView {
                        editText.setText(resultText)
                        if (status.first) viewModel?.plus(solutionId = parentId, argumentLvl = argumentLvl, status.second)
                        else viewModel?.minus(solutionId = parentId, argumentLvl = argumentLvl, status.second)
                    }
                }
            }
        })
    }

    private fun chekTextStatus(text: String?): Pair<Boolean, String>? {
        if (text?.trim()?.isEmpty() == true) return null

        val words = text?.split(" ")?.toMutableList() ?: return null
        if (words.size <= 1) return null

        val status = words.removeAt(0)
        val resultText = words.toList().joinToString(" ")

        val positivePair = Pair(true, resultText)
        val negativePair = Pair(false, resultText)

        return when (status) {
            "+" -> positivePair
            "плюс" -> positivePair
            "Плюс" -> positivePair
            "positive" -> positivePair
            "Positive" -> positivePair
            "Plus" -> positivePair
            "plus" -> positivePair

            "-" -> negativePair
            "минус" -> negativePair
            "Минус" -> negativePair
            "negative" -> negativePair
            "Negative" -> negativePair
            "minus" -> negativePair
            "Minus" -> negativePair
            else -> null
        }
    }

    private fun checkPermissionRecord() {
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.RECORD_AUDIO),
                RecordAudioRequestCode
            )
        } else {

        }
    }

    private fun startVoiceListening() {
    }

    private fun stopVoiceListening() {

    }

    private fun getArgs() {
        parentId = navArgs.solutionId
        binding.editText.hint = navArgs.solutionName
    }

    override fun renderState(state: InsertSolutionToSimpleDetailsViewState) {
        when (state) {
            InsertSolutionToSimpleDetailsViewState.CompletedAddDetails -> {
                findNavController().popBackStack()
                sendEvent(UiEvent.ReloadMainPage)
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
//        sendEvent(UiEvent.ReloadMainPage)
    }
}
