import React, { useState } from 'react'
import {Link, useHistory } from 'react-router-dom';
import "./SignupView.css";

export const SignupView = () => {
    const [state, setState] = useState({
        username: "",
        password: "",
        unique: false,
    });

    const history = useHistory();

    const signupRequest = () => {
        console.log(`signupRequest {${state.username}, ${state.password}}`);
    }

    return (
        <div className = "signup-container">
            <div className = "signup-form-container">
                <h1>회원가입</h1>
                <div className = "signup-form">
                    <div className = "signup-form-row">
                        <div className = "signup-form-username">
                            <label for = "username">유저네임</label>
                            <input 
                                    id = "username"
                                    value = {state.username}
                                    onChange = {e => setState({...state, username: e.target.value})}
                                    ></input>
                        </div>
                        <button className = "signup-form-checkUniqueButton">중복확인</button>
                    </div>
                    <div className = "signup-form-row">
                        <div className = "signup-form-password">
                            <label for = "password">비밀번호</label>
                            <input 
                                    id = "password"
                                    value = {state.password}
                                    type = "password"
                                    placeholder = "6+ characters"
                                    onChange = {e => setState({...state, password: e.target.value})}
                            ></input>
                        </div>
                    </div>
                    <button className = "signup-form-button" onClick = {signupRequest}>회원가입</button>
                </div>
                <div className = "signup-form-linkToSignup">
                    <Link to = "/signin">로그인</Link>
                </div>
            </div>
        </div>
    )
}
