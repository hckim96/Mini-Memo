import React from 'react'
import "./MemoViewTabBar.css";
import {BiDetail, BiUserCircle, BsListCheck, BiLogOut} from "react-icons/all"
import { IconContext } from "react-icons";
import { useHistory } from 'react-router-dom';

export const MemoViewTabBar = () => {
    const history = useHistory();
    const handleLogout = () => {
        localStorage.removeItem("LS_JWT_TOKEN");
        history.push("/signin");
    }
    return (
        <div className = "memoViewTabBar-container">
            <IconContext.Provider value = {{color: "white", size: "2rem", className: "abcd"}}>
            <div className = "memoViewTabBar-header">
                    <div className = "memoViewTabBar-tab">
                        <BiDetail /> 메모
                    </div>
                    <div className = "memoViewTabBar-tab">
                        <BsListCheck /> 할일
                    </div>
            </div>
            <div className = "memoViewTabBar-footer">
                <div className = "memoViewTabBar-tab">
                    <BiUserCircle/>
                </div>
                <div className = "memoViewTabBar-tab" onClick = {handleLogout}>
                    <BiLogOut/>
                </div>
            </div>
            </IconContext.Provider>
        </div>  
    )
}
