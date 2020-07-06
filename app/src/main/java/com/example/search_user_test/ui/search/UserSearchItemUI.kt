package com.example.search_user_test.ui.search

import android.graphics.Color
import android.view.ViewGroup
import android.widget.*
import org.jetbrains.anko.*

class UserSearchItemUI() :
    AnkoComponent<ViewGroup> {

    var mImageView: ImageView? = null

    var mTextView: TextView? = null

    var mFavoriteCheckBox: CheckBox? = null


    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {

        verticalLayout {
            linearLayout {
                mImageView = imageView {
                    backgroundColor = Color.BLUE
                }.lparams {
                    height = dip(60)
                    width = dip(60)
                }

                mTextView = textView {
                }.lparams {
                    height = matchParent
                    width = 0
                    weight = 1f
                }
                mFavoriteCheckBox = checkBox {

                }
                lparams {
                    height = dip(60)
                    width = matchParent
                }
            }
            view {
                backgroundColor = Color.BLACK
            }.lparams {
                width = matchParent
                height = dip(1)
            }
        }

    }

}