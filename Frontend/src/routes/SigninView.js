import React, { useState } from 'react';
import axios from "axios";
import { Link, useHistory } from 'react-router-dom';
// import "./SigninView.css";
import "./SigninView.css";

const API_URL = "http://localhost:8080/api/";

export const SigninView = () => {
    const [state, setState] = useState({
        username: "",
        password: "",
    });
    const history = useHistory();
    
    // const siginIn = ({username, password}) => {
    //     axios.post(API_URL + "signin", {
    //         username: username,
    //         password: password
    //     }).then((res) => {
    //         if (res.status == 200) {
    //             localStorage.setItem("LS_JWT_TOKEN", res.data);
    //             history.push("/memos");
    //         }
    //     }).catch((res) => {
    //         console.log(res);
    //     })
    // }

    return (
        <div className = "signin-container">
            <div className = "signin-form-container">
                <h1>로그인</h1>
                <div className = "signin-form">
                    <div className = "signin-form-username">
                        <label for = "username">유저네임</label>
                        <input 
                                id = "username"
                                value = {state.username}
                                onChange = {e => setState({...state, username: e.target.value})}
                                ></input>
                    </div>
                    <div className = "signin-form-password">
                        <label for = "password">비밀번호</label>
                        <input 
                                id = "password"
                                value = {state.password}
                                type = "password"
                                onChange = {e => setState({...state, password: e.target.value})}
                        ></input>
                    </div>
                    <button className = "signin-form-button" onClick = {() => {history.push("/memos")}}>로그인</button>
                </div>
                <div className = "signin-form-linkToSignup">
                    <Link to = "/signup">회원가입</Link>
                </div>
            </div>
        </div>
    )
}
