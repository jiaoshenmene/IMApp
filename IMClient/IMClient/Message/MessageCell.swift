//
//  MessageCell.swift
//  IMClient
//
//  Created by 杜甲 on 2019/4/18.
//  Copyright © 2019 杜甲. All rights reserved.
//

import UIKit

class MessageCell: BaseTableViewCell {
    
    @IBOutlet weak var portrait: UIImageView!
    
    @IBOutlet weak var name: UILabel!
    
    @IBOutlet weak var mes: UILabel!
    @IBOutlet weak var time: UILabel!
}
