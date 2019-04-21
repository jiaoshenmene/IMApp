//
//  LoginView.swift
//  IMClient
//
//  Created by 杜甲 on 2019/4/20.
//  Copyright © 2019 杜甲. All rights reserved.
//

import UIKit
import ReactiveCocoa
import ReactiveSwift

class LoginView: UIView {
    
    private let viewModel = LoginViewModel()
    
    
    @IBOutlet weak var accountTextField: UITextField!
    @IBOutlet weak var passwordTextField: UITextField!
    
    @IBOutlet weak var loginBtn: UIButton!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    @IBAction func login(_ sender: Any) {
        let account = accountTextField.text!
        
        let password = passwordTextField.text!
        viewModel.login(account, password)
        
    }
}
