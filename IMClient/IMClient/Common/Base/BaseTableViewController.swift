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
    
    
    override func configUI() {
        super.configUI()
        
        self.tableView.delegate = self;
        self.tableView.dataSource = self;
        
        self.tableView.estimatedRowHeight = self.tableView.rowHeight
        self.tableView.rowHeight = UITableView.automaticDimension

    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return listViewModel.numberOfRows(section)
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: self.cellType(indexPath).reuseIdentifier, for: indexPath) as! BaseTableViewCell
        cell.cellModel = listViewModel.cellModel(indexPath)
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return cellHeight(indexPath)
    }
    
    func cellType(_ indexPath: IndexPath) -> BaseTableViewCell.Type {
        return listViewModel.cellType(indexPath) as! BaseTableViewCell.Type
    }
    
    func cellHeight(_ indexPath: IndexPath) -> CGFloat {
        return self.cellType(indexPath).height(listViewModel.cellModel(indexPath))
    }
    
}
