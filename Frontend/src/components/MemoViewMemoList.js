import React from 'react'
import "./MemoViewMemoList.css";
export const MemoViewMemoList = () => {
    const cards = [...Array(40).keys()].map((d) => {
        return <div key = {d}>{d}</div>
    })
    return (
        <div className = "memoViewMemoList-container">
            this is memoList
            {cards}
        </div>
    )
}
