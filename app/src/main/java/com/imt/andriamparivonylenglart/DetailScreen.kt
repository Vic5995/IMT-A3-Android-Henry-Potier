package com.imt.andriamparivonylenglart

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DetailScreen(name: String?, price: String){
    val description = "Après la mort de ses parents (Lily et James Potier), Henri est recueilli par sa tante Pétunia (la sœur de Lily) et son oncle Vernon à l'âge d'un an. Ces derniers, animés depuis toujours d'une haine féroce envers les parents du garçon qu'ils qualifient de gens « bizarres », voire de « monstres », traitent froidement leur neveu et demeurent indifférents aux humiliations que leur fils Dudley lui fait subir. Henri ignore tout de l'histoire de ses parents, si ce n'est qu'ils ont été tués dans un accident de voiture"
    var qty by remember { mutableStateOf(TextFieldValue("1")) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ){
            Text(text = name ?: "Title", fontSize = 30.sp)
            Spacer(modifier = Modifier.height(300.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = price, fontSize = 30.sp)

                OutlinedTextField(
                    value = qty,
                    label = { Text(text = "Qté") },
                    onValueChange = { newQty ->
                        qty = newQty
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.30f)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, bottom = 15.dp)
            ) {
                Button(
                    onClick = {},
                ){
                    Text(text = "Ajouter au panier")
                }
            }
            Text(text = description)
        }
    }
}