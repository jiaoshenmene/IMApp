//
//  RegisterView.swift
//  IMClient
//
//  Created by 杜甲 on 2019/4/20.
//  Copyright © 2019 杜甲. All rights reserved.
//

import UIKit

class RegisterView: UIView {
    
    @IBOutlet weak var phoneTextField: UITextField!
    @IBOutlet weak var nameTextField: UITextField!
    @IBOutlet weak var passwordTextField: UITextField!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
    }
    
    @IBAction func registerMethod(_ sender: Any) {
        let account = phoneTextField.text
        let name = nameTextField.text
        let password = passwordTextField.text
        
        
    }
    
}
