//
//  BaseTableViewController.swift
//  IMClient
//
//  Created by 杜甲 on 2019/4/18.
//  Copyright © 2019 杜甲. All rights reserved.
//
import UIKit
class BaseTableViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet var tableView: UITableView!
    
    var listViewModel: ListViewModel! {
        get {
            return super.baseViewModel as? ListViewModel
        }
        set {
            super.baseViewModel = newValue
        }
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return listViewModel.numberOfRows(section)
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: self.cellType(indexPath).reuseIdentifier, for: indexPath) as! BaseTableViewCell
        print("reuseIdentifier = " + self.cellType(indexPath).reuseIdentifier)
        cell.textLabel?.text = "cell"
        return cell
    }
    
    func cellType(_ indexPath: IndexPath) -> BaseTableViewCell.Type {
        return listViewModel.cellType(indexPath) as! BaseTableViewCell.Type
    }
    
    
}
