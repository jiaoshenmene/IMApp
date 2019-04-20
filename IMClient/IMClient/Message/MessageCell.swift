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
    
    var messageModel:MessageCellModel!
    
    
    override func configUI() {
        super.configUI()
        messageModel = cellModel as? MessageCellModel
    }
    
    override func updateUI() {
        super.updateUI()
        name.text = messageModel.name
        mes.text = messageModel.msg
    }
    
    override class func height(_ data: CellModel?) -> CGFloat {
        return 100
    }
    
    override class func size(_ data: CellModel?) -> CGSize {
        
        return CGSize(width: 100, height: 500)
    }
    
}
