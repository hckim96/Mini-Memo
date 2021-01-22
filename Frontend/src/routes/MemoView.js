import React from 'react'
import { MemoViewMemoList } from '../components/MemoViewMemoList';
import { MemoViewTabBar } from '../components/MemoViewTabBar';
import "./MemoView.css";

export const MemoView = () => {
    return (
        <div className = "memoView-container">
            <MemoViewTabBar></MemoViewTabBar>
            <MemoViewMemoList></MemoViewMemoList>            
            hello
        </div>
    )
}
