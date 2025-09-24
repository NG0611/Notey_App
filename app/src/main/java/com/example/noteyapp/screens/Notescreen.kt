package com.example.noteyapp.screens

import android.R.attr.background
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.noteyapp.ViewModels.NoteViewmodel
import com.example.noteyapp.models.Note
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen() {
    val context= LocalContext.current
    var noteview: NoteViewmodel=viewModel()
    var notes=noteview.notelist.collectAsState().value
    var notetitle by remember {
        mutableStateOf(noteview.notetitle)
    }
    var notedes by remember {
        mutableStateOf(noteview.notedes)
    }
    fun addnote(note:Note){
        noteview.insertnote(note)
    }
    fun removenote(note:Note){
        noteview.deletenote(note)
    }









    Scaffold (
        topBar={
            TopAppBar(
                title = {Text(text = "Notey App", color=Color.White, fontWeight = FontWeight.Bold)},
                actions = {
                    Icon(
                        Icons.Default.Notifications,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 16.dp),
                        tint = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(Color.Black)

            )
        }
    ){

        Column(modifier=Modifier
            .padding(it)
            .fillMaxSize().background(Color(0xFF2F3E46) ),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Spacer(modifier = Modifier.height(20.dp))

            TextField(value = notetitle,
                onValueChange = {notetitle=it},
                label={Text(text = "Title")},
            )
            Spacer(modifier=Modifier.height(20.dp))
            TextField(value=notedes,
                onValueChange = {notedes=it},
                label={Text(text = "Description")},
                )
            Spacer(modifier=Modifier.height(20.dp))
            Button(onClick = {
                addnote(Note(Title = notetitle, Description = notedes))
                notetitle=""
                notedes=""
                Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show() }
            )
            {
                Text(text = "Add Note")
            }

            Spacer(modifier=Modifier.height(20.dp))


            LazyColumn()
            {
                items(notes){
                    NoteRow(note=it,
                        {removenote(it)})

                }

            }

        }

    }
}


@Composable
fun NoteRow(note: Note,
            onNoteclicked:(Note)->Unit) {
    Surface (modifier = Modifier.padding(15.dp).fillMaxWidth(),
        color = Color.Black,
        shape = RoundedCornerShape(16.dp),
        shadowElevation=6.dp,
        border = BorderStroke(1.dp, color = Color.Cyan)
    )
    {
        Row {
            Column(modifier = Modifier.padding(10.dp).weight(1f))
            {
                Text(text = note.Title, fontWeight = FontWeight.Bold, color=Color.White)
                Text(text=note.Description, fontFamily = FontFamily.SansSerif,color=Color.White)
                Text(text = formatDate(note.entryDate.time), fontStyle = FontStyle.Italic,color=Color.White)
            }

            IconButton(onClick = { onNoteclicked(note)},
                colors = IconButtonColors(containerColor = Color.White,
                    contentColor = Color.Black,
                    disabledContentColor = Color.Black,
                    disabledContainerColor = Color.White),
                modifier = Modifier.padding(10.dp))
            {
                Icon(Icons.Default.Delete, contentDescription = "delete")
            }

        }



    }

}
fun formatDate(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("EEE, d MMM hh:mm aaa",
        Locale.getDefault())
    return format.format(date)
}


@Preview(showBackground=true)
@Composable
fun NotescreenPreview() {
    var noteview: NoteViewmodel= viewModel()
    NoteScreen()

}
