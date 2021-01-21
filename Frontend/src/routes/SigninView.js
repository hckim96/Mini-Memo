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
    
    const signinRequest = () => {
        let success = true;
        // axios.post(API_URL + "signin", {
        //     username: state.username,
        //     password: state.password,
        // }).then(res => {
        //     console.log(res);
        //     // save token
        //     // redirect to memos
        //     history.push("/memo")
        // }).catch(res => {
        //     console.log(`catch ${res}`);
        // })
        console.log(`signinRequest {username: ${state.username}, password: ${state.password}}`)
        if (success) {
            history.push("/memo")
        }
    }

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
                    <button className = "signin-form-button" onClick = {signinRequest}>로그인</button>
                </div>
                <div className = "signin-form-linkToSignup">
                    <Link to = "/signup">회원가입</Link>
                </div>
            </div>
        </div>
    )
}
